package tech.xueyao.tooool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.intern.InternUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.dialect.log4j2.Log4j2Log;
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
        log.info("---- [Start]根据条件创建文件夹并移动文件 ----");

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

        String fileSplitText = fileSplit.getText();
        if (StrUtil.isBlank(fileSplitText)) {
            log.info("没有任何条件，操作终止");
            return;
        }

        // 遍历文件夹下的文件
        List<String> fileNames = FileUtil.listFileNames(folderPathStr);
        for (String fileName : fileNames) {
            log.info("文件全名：{}", fileName);
            List<String> split = StrUtil.split(fileName, fileSplitText);
            if (split.size() > 1) {
                String folder = split.stream().findFirst().get();
                String newFolder = folderPathStr + "/" + folder;
                if (ObjectUtil.isNotEmpty(FileUtil.mkdir(newFolder))) {
                    String oldFilePath = folderPathStr + "/" + fileName;
                    String newFilePath = newFolder + "/" + fileName;
                    log.info("开始批量移动文件");
                    FileUtil.move(FileUtil.file(oldFilePath), FileUtil.file(newFilePath),true);
                }
            }
        }

        // 创建并显示通知提示框
        Notifications.create()
                .title("提示")
                .text("操作成功")
                .position(Pos.CENTER)
                .showInformation();

        log.info("---- [End]根据条件创建文件夹并移动文件 ----");
    }

}