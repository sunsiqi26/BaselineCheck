import socket
from const.Type import Type
from scan.Base import Base



#获取主板ID
board_id = Base.get_board_id()
print("欢迎",board_id)

while True:
    ins = input("""
        退出  0
    设备注册  1
    基本信息  2
    安装软件  3
    网络信息  4
    进程信息  5
    服务信息  6
    主机信息  7
    禁用服务  8
    基线检查  9
        请输入指令> """)

    #退出
    if ins == '0':
        break

    #设备注册
    elif ins == '1':
        data = Base.get_register_info(board_id)
        typeCode = Type.REGISTER

    #基本信息
    elif ins == '2':
        data = Base.get_base_info()
        typeCode = Type.BASE_INFO

    #安装的软件
    elif ins == '3':
        data = Base.get_install_soft()
        typeCode = Type.INSTALLED_SOFT

    #网络信息
    elif ins == '4':
        data = Base.get_network_info()
        typeCode = Type.NETWORK_INFO

    #进程信息
    elif ins == '5':
        data = Base.get_process_info()
        typeCode = Type.PROCESS_INFO

    #服务信息
    elif ins == '6':
        data = Base.get_service_info()
        typeCode = Type.SERVICE_INFO

    #主机信息
    elif ins == '7':
        data = Base.get_host_info()
        typeCode = Type.HOST_INFO

    #禁用服务
    elif ins == '8':
        data = Base.disable_service()
        typeCode = Type.DISABLE_SERVICE

    #基线检查
    elif ins == '9':
        data = Base.baseline_inspection()
        typeCode = Type.BASELINE_SERVICE

    else:
        print("\n(・_・)ﾝ?  ",ins)
        continue


    data = data.replace("\"","'")
    msg = '{"typeCode":%d, "boardId":"%s", "data":"%s"}' % (typeCode, board_id, data)
    #print("\n",msg)

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect(('127.0.0.1',8008))
    sock.send(msg.encode('utf-8'))
    print("\n",'发送数据成功!',"\n")
    sock.close()

print("\n拜拜(^_^)/~")
