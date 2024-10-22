import os
import PySimpleGUI as sg

class FileRenamerApp:
    def __init__(self):
        # 定义菜单布局（模拟工具栏选项）
        menu_def = [
            ['帮助', ['关于']]
        ]

     
        sg.set_options(font=("黑体", 12))

        self.layout = [
            [sg.Menu(menu_def)],
            [sg.Text("选择文件夹：")],
            [sg.Input(key='-FOLDER_PATH-'), sg.FolderBrowse()],
            [sg.Text("文件名前缀：")],
            [sg.Input(key='-PREFIX-')],
            [sg.Text("文件名后缀：")],
            [sg.Input(key='-SUFFIX-')],
            [sg.Button("开始修改"), sg.Button("退出")]
        ]
        self.window = sg.Window("文件批量修改工具", self.layout)

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
                if folder_path:
                    self.rename_files(folder_path, prefix, suffix)
                    sg.popup("批量文件名修改完成！", title = "提示")
                else:
                    sg.popup("请选择要修改的文件夹！", title = "警告")
        self.window.close()

    def rename_files(self, folder_path, prefix, suffix):
        for filename in os.listdir(folder_path):
            old_path = os.path.join(folder_path, filename)
            if os.path.isfile(old_path):
                new_filename = f"{prefix}{filename}{suffix}"
                new_path = os.path.join(folder_path, new_filename)
                os.rename(old_path, new_path)



if __name__ == '__main__':
    app = FileRenamerApp()
    app.run()