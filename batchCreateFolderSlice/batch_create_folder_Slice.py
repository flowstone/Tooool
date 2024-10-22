import os
import shutil
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
            [sg.Text("说明：根据输入的分割字符，取前部分创建文件夹，符合相关的文件都移动到对应文件夹中", font=("楷体", 10), text_color='white')],
            #[sg.Menu(menu_def)],
            [sg.Text("选择文件夹：")],
            [sg.Input(key='-FOLDER_PATH-'), sg.FolderBrowse()],
            [sg.Text("指定分割字符：")],
            [sg.Input(key='-SLICE-')],
            
            [sg.Text("Author：xueyao.me@gmail.com", font=("楷体", 10), text_color='blue')],
            [sg.Text("Github：https://github.com/flowstone/Tooool", font=("楷体", 10), text_color='blue')],
            [sg.Button("开始"), sg.Button("退出")],

        ]
        self.window = sg.Window("批量移动文件", self.layout)


    def run(self):
        while True:
            event, values = self.window.read()
            if event == sg.WINDOW_CLOSED or event == "退出":
                break
            elif event == "开始":
                folder_path = values['-FOLDER_PATH-']
                slice = values['-SLICE-']
                
                if folder_path:
                    self.create_folder_move_files(folder_path, slice)
                    sg.popup("移动文件完成！", title = "提示")
                else:
                    sg.popup("请选择要操作的文件夹！", title = "警告")
        self.window.close()

    # 创建文件夹，并移动到指定目录下
    def create_folder_move_files(self, folder_path, slice):

        # 遍历文件夹下的文件名
        for filename in os.listdir(folder_path):
            source_path = os.path.join(folder_path, filename)
            # 判断是否是文件
            if os.path.isfile(source_path):
                # 找到分割的位置，如'-'
                index = filename.find(slice)
                if index!= -1:
                    # 提取 '-' 前面的部分作为文件夹名
                    folder_name = filename[:index]
                    # 如果文件夹不存在，则创建
                    target_folder = os.path.join(folder_path, folder_name)
                    if not os.path.exists(target_folder):
                        os.mkdir(target_folder)
                    # 将图片移动到对应的文件夹
                    destination_path = os.path.join(target_folder, filename)
                    shutil.move(source_path, destination_path)

   

if __name__ == '__main__':
    app = FileRenamerApp()
    app.run()