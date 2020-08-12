# coding=utf-8

#检测结果集合
outstr=["不合规","合规"]


#建立编号和用户组映射的字典
user_check_dict = {}
with open ('scan/file/User_Rights_table.csv') as f:
    for i in f:
        user_check_dict[i.split(',')[0]] = i.split(',')[1]


def intcompare(carry, mid):
    cmp = []
    ##有,为大小比较
    if ',' in carry:
        p = carry.split(',')
        if (p[0] != ''):
            cmp.append(int(p[0]))
        cmp.append(int(mid))
        if (p[1] != ''):
            cmp.append(int(p[1]))
        ##利用sorted排序验证大小
        return cmp == sorted(cmp)

    else:
        return carry == mid

#将数字等转为文字
def trans(arr):
    if arr == '0':
        arr = "禁用"
    elif arr == '1':
        arr = "启用"
    else:
        arr = arr.replace('"','')
    return arr

'''
判断[System Access]
'''
def judge(temp=[2]):
    # 数字判断
    ##用[]为数字
    if '[' in temp[0]:
        temp[0] = temp[0].strip('[]')
        return temp[1], outstr[intcompare(temp[0], temp[1])]

    # 字符串判断
    else:
        temp[1]=trans(temp[1])
        return temp[1], outstr[temp[0]==temp[1]]



'''
判断[Event Audit]
'''
def judge_audit(temp=[2]):
    if temp[1] == '0':
        temp[1] = '无审核'
    elif temp[1] == '1':
        temp[1] = '成功'
    elif temp[1] == '2':
        temp[1] = '失败'
    elif temp[1] == '3':
        temp[1] = '成功,失败'
    return temp[1],outstr[temp[0]==temp[1]]



'''
判断[Privilege Rights]
'''
def judge_rights(temp=[2]):
    standard = temp[0]
    real = temp[1]
    real_right = []
    real_right_str = ''
    comma = ','

    #去掉*号(*:asterisk)
    real = real.replace('*','')
    #以逗号分隔
    real = real.split(',')
    standard = standard.split(',')

    standard.sort()
    #将 代号 转换为 用户组名
    for i in real:
        if i not in user_check_dict:
            real_right.append(i)
        if i in user_check_dict:
            real_right.append(user_check_dict[i])
    real_right.sort()

    #转为str
    real_right_str = comma.join(real_right)

    #判断 建议权限 是否包含 现实权限
    if len(real_right) == len(standard) == 1:       #针对单复数的情况
        return real_right_str,outstr[real_right[0] in standard[0]]
    else:
        return real_right_str,outstr[set(real_right) <= set(standard)]



'''
判断[Registry Values]
'''
def judge_registry_values(standard,explain,real):
    real = real.split(',')[1]

    #0,1,2,3等情况
    if ':' in explain:
        explain_dic = {}
        explain = explain.split(';')
        for i in explain:
            explain_dic[i.split(':')[0].split(',')[1]] = i.split(':')[1]

        return outstr[explain_dic[real] == standard],explain_dic[real]

    #[1,30],[,4]等情况
    if '[' in standard:
        real = real.replace('"','') #针对[1,'10']情况
        standard = standard.strip('[]')
        standard_range = standard.split(',')
        if standard_range[0] == '': standard_range[0] = 0
        return outstr[int(real) >= int(standard_range[0]) and int(real) <= int(standard_range[1])],real

    #路径情况
    if '\\' in standard:
        real_path = real.split(' ')
        standard_path = standard.split(' ')
        real_path.sort()
        standard_path.sort()
        return outstr[set(real_path) <= set(standard_path)],real
    
    #not none情况
    real = real.replace('"','') #针对""情况
    if (standard == 'not none') and (real != ''):
        return '合规',real

    #'Administrators'/'Posix'情况
    return outstr[standard == real],real


