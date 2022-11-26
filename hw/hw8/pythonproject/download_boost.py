import os

if __name__ == "__main__":
    ini = "[global]\nindex-url = https://mirrors.aliyun.com/pypi/simple/\n"
    pippath=os.environ["USERPROFILE"]+"\\pip\\"
    exec("if not os.path.exists(pippath):\n\tos.mkdir(pippath)")
    open(pippath+"/pip.ini","w+").write(ini)
