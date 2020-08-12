import wmi
import json
import datetime
import winreg
import psutil
from scan import hostinfo as h
import platform
import csv
from scan import disableservice as ds
from scan import getinfo as getinfo
from scan import baseline_inspection as bain


class Base:
    """扫描的类"""

    @staticmethod
    def get_register_info(boardId):
        """获取注册信息"""
        # 获取主板id
        board_id = boardId
        # 获取电脑的名字
        computer_name = Base.get_computer_name()
        return "{'boardId':'%s','computerName':'%s'}" % (board_id, computer_name)

    @staticmethod
    def get_board_id():
        """获取主板的ID"""
        # 获取wmi对象
        c = wmi.WMI()
        # 读取主板的信息
        board_info = c.Win32_BaseBoard()
        # 获取具体的主板id
        board_id = board_info[0].SerialNumber.strip()
        return board_id

    @staticmethod
    def get_computer_name():
        """获取电脑的名字"""
        # 获取wmi对象
        c = wmi.WMI()
        wmi_win32_operating_system = c.Win32_OperatingSystem()[0]
        computer_name = wmi_win32_operating_system.CSName
        return computer_name

    @staticmethod
    def get_base_info():
        """获取当前设备的基本信息"""
        w = wmi.WMI()
        # 获取计算机运行环境信息
        wmi_win32_operating_system = w.Win32_OperatingSystem()[0]
        # 获取计算机CPU数量,内存大小,主板相关信息
        wmi_win32_computer_system = w.Win32_ComputerSystem()[0]
        # 用于获取CPU详细信息
        wmi_win32_processor = w.Win32_Processor()[0]
        # 组装数据
        info = {
            "操作系统版本": wmi_win32_operating_system.Caption,
            "计算机名字": wmi_win32_operating_system.CSName, 
            "系统本地时间": str(
                datetime.datetime.strptime(str(str(wmi_win32_operating_system.LocalDateTime).split('.')[0]),
                                           '%Y%m%d%H%M%S')),  
            "系统上次启动时间": str(
                datetime.datetime.strptime(str(str(wmi_win32_operating_system.LastBootUpTime).split('.')[0]),
                                           '%Y%m%d%H%M%S')),  
            "操作系统类型": wmi_win32_operating_system.OSArchitecture,  # 获取操作系统类型(32bit/64bit)
            "操作系统语言版本": wmi_win32_operating_system.MUILanguages[0], 
            "操作系统序列号": wmi_win32_operating_system.SerialNumber,  
            "cpu数量": wmi_win32_computer_system.NumberOfProcessors,
            # 获取cpu数量 这里获取的是外部硬件规格，家用机电脑，主板只能安装一个cpu规格，所有获取为1
            "主板厂商信息": wmi_win32_computer_system.Manufacturer,
            "主板型号": wmi_win32_computer_system.Model,
            "主板架构类型": wmi_win32_computer_system.SystemType,
            "内存容量": int(wmi_win32_computer_system.TotalPhysicalMemory) / 1024 / 1024,
            "cpu类型": wmi_win32_processor.Name,
            "操作系统CPU主频": wmi_win32_processor.MaxClockSpeed,
            "CPU核心数量": wmi_win32_processor.NumberOfCores,
            "计算机的CPU数据宽度": wmi_win32_processor.DataWidth,
            "主板cpu接口类型": wmi_win32_processor.SocketDesignation,
            "cpu二级缓存大小": wmi_win32_processor.L2CacheSize,
            "cpu三级缓存大小": wmi_win32_processor.L3CacheSize 
        }
        return json.dumps(info)

    @staticmethod
    def get_install_soft():
        """扫描安装软件列表"""
        # 定义注册表安装卸载软件检测位置，下列两种位置都可以找到
        # r'SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall'
        # r'SOFTWARE\Wow6432Node\Microsoft\Windows\CurrentVersion\Uninstall'
        sub_key = r'SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall'

        # 把注册表对象转换为python对象
        key = winreg.OpenKey(winreg.HKEY_LOCAL_MACHINE, sub_key, 0, winreg.KEY_ALL_ACCESS)
        software_name = []
        # 通过winreg.QueryInfoKey(key)[0]获取当前注册表中的总个数
        for j in range(0, winreg.QueryInfoKey(key)[0] - 1):
            try:
                # 获取应用软件的尾缀名称
                key_name = winreg.EnumKey(key, j)
                # 组装完整的注册表软件路径
                key_path = sub_key + '\\' + key_name
                each_key = winreg.OpenKey(winreg.HKEY_LOCAL_MACHINE, key_path, 0, winreg.KEY_ALL_ACCESS)

                display_name, rgz = winreg.QueryValueEx(each_key, 'DisplayName')
                software_name.append(display_name)
            except WindowsError:
                pass

        software_name = list(set(software_name))
        software_name = sorted(software_name)
        return json.dumps(software_name)

    # 网络信息转dict
    @staticmethod
    def network2dict(obj):
        caption = str(obj.Caption)[11:]
        info = {
            "caption": caption,
            "IPV4地址": obj.IPAddress[0],
            "子网掩码": obj.IPSubnet[0],
            "MAC地址": obj.MACAddress
        }

        return info
    
    @staticmethod
    def get_network_info():
        """获取网络配置信息"""
        # 实例化wmi类
        w = wmi.WMI()
        #  配置及获取网络连接相关信息
        wmi_win32_network_adapter_configuration = w.Win32_NetworkAdapterConfiguration(IPEnabled=1)
        return json.dumps(wmi_win32_network_adapter_configuration, default=Base.network2dict)


    @staticmethod
    def get_process_info():
        """获取进程信息"""
        obj = psutil.pids()
        data = []

        for pid in obj:
            try:
                p = psutil.Process(pid)

                info = {
                    "进程id":pid,
                    "进程名":p.name(),
                    "进程工作目录":p.cwd().replace("\\","/"),
                    "进程启动命令行":str(p.cmdline()).replace("'","").replace('"',"").replace("\\\\","/"),
                    "父进程":str(p.parent()).replace("'",""),
                    "进程状态":p.status(),
                    "进程用户名":p.username().replace("\\","/"),
                    "进程创建时间":p.create_time(),
                    "进程使用的内存":str(p.memory_info())
                }

            except:
                pass

            else:
                data.append(info)
                #print(info["进程启动命令行"])

        return json.dumps(data)


    # 服务基线转dict
    @staticmethod
    def service2dict(obj):
        # 处理描述信息
        description = str(obj.Description)
        description = description.replace('"', '')
        description = description.replace('\r\n', '')

        # 处理路径
        pathname = str(obj.PathName)
        pathname = pathname.replace('"', '')
        pathname = pathname.replace('\\', '/')


        info = {
            "caption": obj.Caption,
            "pathName": pathname,
            "description": description,
            "started": obj.Started,
            "startMode": obj.StartMode,
            "state": obj.State,
            "status": obj.Status,            
            "acceptPause": obj.AcceptPause,
            "acceptStop": obj.AcceptStop,
            "delayedAutoStart": obj.DelayedAutoStart,
            "errorControl": obj.ErrorControl,
            "exitCode": obj.ExitCode,
            "serviceSpecificExitCode": obj.ServiceSpecificExitCode,
            "creationClassName": obj.CreationClassName,
            "systemCreationClassName": obj.SystemCreationClassName,
        }
        return info

    @staticmethod
    def get_service_info():
        """获取服务信息"""
        w = wmi.WMI()
        # 获取服务相关信息
        wmi_win32_service = w.Win32_Service()
        return json.dumps(wmi_win32_service, default=Base.service2dict)


    @staticmethod
    def get_host_info():
        """获取主机信息"""
        os = platform.uname()[0] + " "+platform.uname()[2] + "v" +platform.uname()[3]#操作系统
        cpu = platform.uname()[5] + " " +str(psutil.cpu_percent(0))+"%"#CPU

        cmd="netstat -ano | findstr LISTENING"
        pattern=r"[0-9]+.[0-9]+.[0-9]+.[0-9]+:(\d+)"

        used_mem,total_mem,mem_use_rate=h.mem_info()#memory
        autoruns = h.get_autoruns()#自启动项
        dic_fix,durn = h.update_information()#Windows更新列表

        info = {
            "IP": h.getip(),
            "主机名称": platform.uname()[1],
            "操作系统": os,
            "CPU": cpu,
            "开放端口": h.open_port(cmd,pattern),
            "已用内存": used_mem,
            "总内存": total_mem,
            "内存占用率": str(mem_use_rate)+"%",
            "自启动项总数": len(autoruns),
            "自启动项": autoruns,
            "更新补丁总数": len(dic_fix),
            "更新补丁": dic_fix,
            "未更新时间": str(durn)+"天"
            }
        return json.dumps(info)


    @staticmethod
    def disable_service():
        """禁用服务"""
        with open('scan/file/disable_service.csv','r',encoding='GB2312') as f:
            reader = csv.reader(f)
            source = list(reader)

        for line in source[1:]:
            line[4],line[5] = ds.judge_type(line[2:4])

        return json.dumps(source)

    @staticmethod
    def baseline_inspection():
        """基线检查"""
        with open('scan/file/all.csv','r') as f:
            reader=csv.reader(f)
            result = list(reader)

        dic=getinfo.getdic()

        for line in result[1:]:
            find = line[3].lower()

            #判断System Access
            if find in dic['System Access']:
                line[6] = dic['System Access'][find]
                line[6], line[7] = bain.judge(line[5:7])

            #判断Event Audit
            if find in dic['Event Audit']:
                line[6]=dic['Event Audit'][find]
                line[6], line[7] = bain.judge_audit(line[5:7])

            #判断Privilege Rights
            if find in dic['Privilege Rights']:
                line[6]=dic['Privilege Rights'][find]
                line[6], line[7] = bain.judge_rights(line[5:7])

            #检查[Registry Values]
            if find in dic['Registry Values']:
                line[6] = dic['Registry Values'][find]
                line[7], line[6] = bain.judge_registry_values(line[5],line[4],line[6])

            ###未设置的用户权限分配
            if (line[5] == 'No One') and (line[6] == ''):
                line[6] = 'No One'
                line[7] = "合规"
            if (line[5] == '') and (line[6] == ''):
                line[7] = "合规"
        
            ###若无建议值
            if line[5]=='':
                line[7] = '合规'
            if (line[5] == 'none') and (line[6] == ''):
                line[7] = "合规"

            line[4] = line[4].replace('"','')
            line[7] = "不合规"

        return json.dumps(result)
