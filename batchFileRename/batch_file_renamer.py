import os
import PySimpleGUI as sg
import tkinter as tk
from PIL import Image

class FileRenamerApp:
    def __init__(self):
        # 定义菜单布局（模拟工具栏选项）
        menu_def = [
            ['帮助', ['关于']]
        ]
        sg.set_options(font=("黑体", 12))

        # 图片路径
        #image_path = "img/backgroud.png"
        #image = Image.open(image_path)
        #original_width, original_height = image.size
        # 假设我们想将宽度设置为300像素，同时保持宽高比
        #new_width = 480
        #new_height = int(original_height * (new_width / original_width))
        #image_element = sg.Image(filename = image_path,size=(new_width,new_height))

        self.layout = [
            [sg.Menu(menu_def)],
            [sg.Text("选择文件夹：")],
            [sg.Input(key='-FOLDER_PATH-'), sg.FolderBrowse()],
            [sg.Text("文件名前缀：")],
            [sg.Input(key='-PREFIX-')],
            [sg.Text("文件名后缀：")],
            [sg.Input(key='-SUFFIX-')],
            [sg.Text("查找字符：")],
            [sg.Input(key='-CHAR_TO_FIND-')],
            [sg.Text("替换字符：")],
            [sg.Input(key='-REPLACE_CHAR-')],
            [sg.Text("Author：xueyao.me@gmail.com", font=("楷体", 11), text_color='blue')],
            [sg.Text("Github：https://github.com/flowstone/Tooool", font=("楷体", 11), text_color='blue')],
            [sg.Button("开始修改"), sg.Button("退出")],

        ]
        self.window = sg.Window("文件名批量修改工具", self.layout)


    def run(self):
        while True:
            event, values = self.window.read()
            if event == "关于":
                sg.popup("只限于修改文件夹下文件！", title = "关于")
            if event == sg.WINDOW_CLOSED or event == "退出":
                break
            elif event == "开始修改":
                folder_path = values['-FOLDER_PATH-']
                prefix = values['-PREFIX-']
                suffix = values['-SUFFIX-']
                charToFind = values['-CHAR_TO_FIND-']
                replaceChar = values['-REPLACE_CHAR-']
                if folder_path:
                    self.rename_files(folder_path, prefix, suffix, charToFind, replaceChar)
                    sg.popup("批量文件名修改完成！", title = "提示")
                else:
                    sg.popup("请选择要修改的文件夹！", title = "警告")
        self.window.close()

    # 修改文件名
    def rename_files(self, folder_path, prefix, suffix, charToFind, replaceChar):
        # 遍历文件夹下的文件名
        for filename in os.listdir(folder_path):
            old_path = os.path.join(folder_path, filename)
            # 判断是否是文件
            if os.path.isfile(old_path):
                new_filename = f"{prefix}{filename}{suffix}"
                # 判断是否需要进行文件替换操作
                if charToFind and replaceChar:
                    # 替换字符
                    new_filename = new_filename.replace(charToFind, replaceChar)
                new_path = os.path.join(folder_path, new_filename)
                os.rename(old_path, new_path)

   

if __name__ == '__main__':
    app = FileRenamerApp()
    app.run()