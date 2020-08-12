import platform
import socket
import subprocess
import re
import psutil
import winreg
import wmi
from datetime import datetime
import json


def getip():
    # 获取本机计算机名称
    hostname = socket.gethostname()
    # 获取本机IP
    ip = socket.gethostbyname(hostname)
    return ip


def run_code(cmd):
    res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    for line in res.communicate():
        return (line.decode("UTF-8"))


#获取开放端口
def open_port(cmd,pattern):
    result=run_code(cmd)
    result=re.findall(pattern,result)
    result=list(set(result))
    result.remove("0")
    return result


#获取内存信息
def mem_info():
    mem=psutil.virtual_memory()
    total_mem=mem.total/(1024*1024)
    used_mem=mem.used/(1024*1024)
    mem_use_rate=mem.percent
    return used_mem,total_mem,mem_use_rate

#获取自启动项
def get_autoruns():
    # 1. 连接注册表根键
    root1 = winreg.ConnectRegistry(None,winreg.HKEY_LOCAL_MACHINE)
    root2 = winreg.ConnectRegistry(None,winreg.HKEY_CURRENT_USER)
    result = {}
    try:
        # 2. 指定想要访问的子健
        reg_path = r"SOFTWARE\Microsoft\Windows\CurrentVersion\Run"
        # 3. 打开相应子健
        key1 = winreg.OpenKey(root1, reg_path)  #打开localmachine的autorun列表
        key2 = winreg.OpenKey(root2, reg_path)  #打开currentuser的autorun列表
        try:
            count = 0
            while(1):
                try:
                    # 4. 通过winreg对象的EnumValue()方法迭代其中的键值
                    n, v, _ = winreg.EnumValue(key1, count)
                    v = v.replace('"','')
                    v = v.replace('\\','/')
                    result[n] = v
                    count += 1
                except EnvironmentError:
                    break
            count = 0
            while(1):
                try:
                    n, v, _ = winreg.EnumValue(key2, count)
                    v = v.replace('"','')
                    v = v.replace('\\','/')
                    result[n] = v
                    count += 1
                except EnvironmentError:
                    break
        finally:
            # 5. 关闭相应子健
            winreg.CloseKey(key1)
            winreg.CloseKey(key2)
    finally:
        # 6. 关闭相应根键连接
        winreg.CloseKey(root1)
        winreg.CloseKey(root2)
    return result


# 检测补丁安装情况
def update_information():
    #用于获取windows更新补丁相关信息
    update_obj = wmi.WMI().Win32_QuickFixEngineering()
    dic_fix = []
    for s in update_obj:
        dic_fix.append((s.HotFixID+" "+s.InstalledOn+" "+s.Description+" "+s.InstalledBy.replace("\\","/")))

    #计算多久未更新
    last_time=datetime.strptime(dic_fix[-1].split(' ')[1],'%m/%d/%Y')
    curr_time=datetime.now()
    durn=(curr_time-last_time).days
    return dic_fix,durn
