package tech.xueyao.tooool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.util.List;

public class BatchCreateFolderController {
    @FXML
    private TextField folderPath;
    @FXML
    private TextField fileSplit;



    /**
     * 文件夹浏览操作
     * @param event
     */

    @FXML
    protected void openFolderChooser1(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        // 设置对话框标题
        directoryChooser.setTitle("选择文件夹");
        // 获取当前窗口（这里假设按钮所在的窗口是主窗口）
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory!= null) {
            System.out.println("选择的文件夹是: " + selectedDirectory.getAbsolutePath());
            folderPath.setText(selectedDirectory.getAbsolutePath());
        }
    }


    @FXML
    protected void onStart1() {
        String folderPathStr = folderPath.getText();
        if (StrUtil.isBlank(folderPathStr)) {
            System.out.println("请选择文件夹!!!");
            // 创建并显示通知提示框
            Notifications.create()
                    .title("提示")
                    .text("请选择文件夹")
                    .position(Pos.TOP_CENTER)
                    .showWarning();
            return;
        }

        String fileSplitText = fileSplit.getText();
        if (StrUtil.isBlank(fileSplitText)) {
            System.out.println("没有任何条件，操作终止");
            return;
        }
        // 遍历文件夹下的文件
        //File[] ls = FileUtil.ls(folderPathStr);
        List<String> fileNames = FileUtil.listFileNames(folderPathStr);
        for (String fileName : fileNames) {
            System.out.println("全名：" + fileName);
            List<String> split = StrUtil.split(fileName, fileSplitText);
            if (split.size() > 1) {
                String folder = split.stream().findFirst().get();
                String newFolder = folderPathStr + "/" + folder;
                if (ObjectUtil.isNotEmpty(FileUtil.mkdir(newFolder))) {
                    String oldFilePath = folderPathStr + "/" + fileName;
                    String newFilePath = newFolder + "/" + fileName;
                    System.out.println("开始批量移动文件");
                    FileUtil.move(FileUtil.file(oldFilePath), FileUtil.file(newFilePath),true);
                }
            }
        }

/*for (File file : ls) {
            if (FileUtil.isFile(file)) {
                // 文件名+包含扩展名  abc.mp3
                String name = FileUtil.getName(file);
                // 文件名前缀 不包含扩展名  abc
                String prefix = FileUtil.getPrefix(file);
                // 文件名后缀 mp3
                String suffix = FileUtil.getSuffix(file);
                System.out.println("全名："+name+",前缀："+prefix+",后缀："+suffix);

                List<String> split = StrUtil.split(prefix, fileSplitText);
                if (split.size() > 1) {
                    String folder = split.stream().findFirst().get();
                    String newFolder = folderPathStr + "/" + folder;
                    if (ObjectUtil.isNotEmpty(FileUtil.mkdir(newFolder))) {
                        String oldFilePath = folderPathStr + "/" + name;
                        String newFilePath = newFolder + "/" + name;
                        System.out.println("开始批量移动文件");
                        FileUtil.move(FileUtil.file(oldFilePath), FileUtil.file(newFilePath),true);
                    }
                }
                // 添加前缀名

            }
        } */

        // 创建并显示通知提示框
        Notifications.create()
                .title("提示")
                .text("操作成功")
                .position(Pos.CENTER)
                .showInformation();

    }
}