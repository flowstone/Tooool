package tech.xueyao.tooool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

import java.io.File;

public class BatchUpdateFilenameController {
    @FXML
    private TextField folderPath;
    @FXML
    private TextField filePrefix;
    @FXML
    private TextField fileSuffix;
    @FXML
    private TextField fileFind;
    @FXML
    private TextField fileReplace;

    private final Log log = LogFactory.get();

    /**
     * 文件夹浏览操作
     * @param event
     */
    @FXML
    protected void openFolderChooser(ActionEvent event) {
        log.info("---- [Start]文件夹选择器 ----");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        // 设置对话框标题
        directoryChooser.setTitle("选择文件夹");
        // 获取当前窗口（这里假设按钮所在的窗口是主窗口）
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory!= null) {
            log.info("选择的文件夹是: {}", selectedDirectory.getAbsolutePath());
            folderPath.setText(selectedDirectory.getAbsolutePath());
        }

        log.info("---- [End]文件夹选择器 ----");
    }


    @FXML
    protected void onStart() {
        log.info("---- [Start]批量修改文件名 ----");

        String folderPathStr = folderPath.getText();
        if (StrUtil.isBlank(folderPathStr)) {
            log.warn("请选择文件夹!!!");
            // 创建并显示通知提示框
            Notifications.create()
                    .title("提示")
                    .text("请选择文件夹")
                    .position(Pos.TOP_CENTER)
                    .showWarning();
            return;
        }
        // 遍历文件夹下的文件
        File[] ls = FileUtil.ls(folderPathStr);
        for (File file : ls) {
            if (FileUtil.isFile(file)) {
                // 文件名+包含扩展名  abc.mp3
                String name = FileUtil.getName(file);
                // 文件名前缀 不包含扩展名  abc
                String prefix = FileUtil.getPrefix(file);
                // 文件名后缀 mp3
                String suffix = FileUtil.getSuffix(file);
                log.info("文件全名：{}, 前缀：{}, 后缀：{}",name, prefix, suffix);
                // 添加前缀名
                String filePrefixText = filePrefix.getText();
                if (StrUtil.isNotBlank(filePrefixText)) {
                    prefix = filePrefixText + prefix;
                }

                String fileSuffixText = fileSuffix.getText();
                // 修改后缀名
                if (StrUtil.isNotBlank(fileSuffixText)) {
                    suffix = fileSuffixText;
                }

                // ------- 查找并替换文件名 --------
                String fileFindText = fileFind.getText();
                String fileReplaceText = fileReplace.getText();
                if (StrUtil.isNotBlank(fileFindText)) {
                    prefix = StrUtil.replace(prefix, fileFindText, fileReplaceText);
                }

                String fileNewName = prefix + "."+ suffix;
                if (!StrUtil.equals(name, fileNewName)) {
                    log.info("开始批量修改文件名");
                    FileUtil.rename(file, fileNewName, true);
                }


            }
        }
        // 创建并显示通知提示框
        Notifications.create()
                .title("提示")
                .text("操作成功")
                .position(Pos.CENTER)
                .showInformation();

        log.info("---- [End]批量修改文件名 ----");
    }
}