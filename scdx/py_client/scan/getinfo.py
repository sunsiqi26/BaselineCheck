# coding: utf-8
import os
import chardet
import configparser
import time

'''
功能：获取系统本地安全策略信息

返回字典调用方法：
policy_dict['System Access']['MinimumPasswordLength ']

'''


def getdic():

    #cmd运行命令
    #os.popen('secedit /export /cfg scan/file/secpolicy.cfg')
    time.sleep(2)

    #获取文件编码类型 UTF-16
    data = open(os.path.join("scan/file/secpolicy.cfg"),'rb').read()
    file_encoding = chardet.detect(data).get('encoding')

    #读取信息，转为字典
    conf = configparser.ConfigParser()
    conf.read("scan/file/secpolicy.cfg",file_encoding)
    policy_dict = dict(conf._sections)
    for i in policy_dict:
        policy_dict[i] = dict(policy_dict[i])

    return policy_dict
