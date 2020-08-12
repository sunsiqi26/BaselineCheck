import subprocess
import re

'''
NotInstalled:指定的服务未安装
Disabled:DISABLED
'''
#检测结果集合
outstr = ["不合规","合规"]


#从cmd获取结果
def getstr(cmd):
    res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    tmp = ""
    for line in res.communicate():
        tmp += line.decode("GB2312")
    return tmp


#查看是否禁用
def judge_type(temp = [2]):
    cmd = "sc qc "+temp[0]
    result = getstr(cmd)
    pattern = r"START_TYPE +: \d +(\w+)\r\n"
    service = re.findall(pattern,result)
    if len(service)==0:
        real = "NotInstalled"
        judge = outstr["NotInstalled" in temp[1]]
    else:
        real = service[0].capitalize()
        judge = outstr[temp[1]=="Disabled"]
    
    return real,judge







