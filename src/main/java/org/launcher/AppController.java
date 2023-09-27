package org.launcher;
//====================================================================================
//PixelTail-AutoLaunch-AppController
//为像素尾巴启动器前端提供控制器
//====================================================================================
//------------------------------------------------------------------------------------
//导入必要的依赖
//------------------------------------------------------------------------------------
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.to2mbn.jmccc.launch.LaunchException;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.Map;
//------------------------------------------------------------------------------------
//声明AppController公共类
//------------------------------------------------------------------------------------
public class AppController {
    //--------------------------------------------------------------------------------
    //为控件创建字段
    //--------------------------------------------------------------------------------
    public TextField UserName;
    public PasswordField PassWord;
    public TextField Version;
    public TextField RootDir;
    public TextField MaxMemory;
    public TextField Width;
    public TextField Height;
    public TextField JreDir;
    public TextField McRuntimeDir;
    public TextField JavaAgent;
    public RadioButton costumeSize;
    public RadioButton defaultSize;

    @FXML
    protected void onHelloButtonClick() throws LaunchException, IOException {
        //--------------------------------------------------------------------------------
        //定义启动按钮事件
        //--------------------------------------------------------------------------------
        //获取输入的内容
        String username = UserName.getText();
        String password = PassWord.getText();
        String version = Version.getText();
        String root_dir = RootDir.getText();
        int max_memory = Integer.parseInt(MaxMemory.getText());
        int width = Integer.parseInt(Width.getText());
        int height = Integer.parseInt(Height.getText());
        String jre_dir = JreDir.getText();
        String mc_runtime_dir = McRuntimeDir.getText();
        String javaagent = JavaAgent.getText();
        //写配置文件
        writeConfig();
        //Minecraft，启动！
        Main.mcLaunch(username, password, version, root_dir, max_memory, width, height, jre_dir, mc_runtime_dir, javaagent);
    }

    @FXML
    protected void onHelpButtonClick() throws IOException {
        //--------------------------------------------------------------------------------
        //定义帮助按钮事件
        //--------------------------------------------------------------------------------
        //创建帮助场景
        Stage helpStage = new Stage();
        //配置fxml加载器并从helpFront.xml加载控件布局
        FXMLLoader helpFxmlLoader = new FXMLLoader(AppController.class.getResource("HelpWindow.fxml"));
        //创建场景并加入布局
        Scene helpScene = new Scene(helpFxmlLoader.load(), 570, 320);
        //----------------------------------------------------------------------------
        //配置窗口设置
        //----------------------------------------------------------------------------
        //设置标题
        helpStage.setTitle("PixelTail一键启动 - 帮助");
        //设置场景
        helpStage.setScene(helpScene);
        //----------------------------------------------------------------------------
        //展示窗口
        //----------------------------------------------------------------------------
        //显示帮助窗口
        helpStage.show();
        //置顶帮助窗口
        helpStage.setAlwaysOnTop(true);
    }

    public void initialize() throws IOException {
        //----------------------------------------------------------------------------
        //重写初始化方法，读配置文件
        //----------------------------------------------------------------------------
        //实例化File对象
        File configFile = new File("config.yml");
        //----------------------------------------------------------------------------
        //判断配置文件是否存在
        //----------------------------------------------------------------------------
        if(configFile.exists()){
            //如果存在，直接开始读取
            // 创建一个YAML对象
            Yaml yaml = new Yaml();
            // 使用FileInputStream加载YAML文件
            InputStream inputStream = new FileInputStream("config.yml");
            // 解析YAML文件内容为Map对象
            Map<String, Object> data = yaml.load(inputStream);
            //------------------------------------------------------------------------
            //判断配置文件是否为空
            //------------------------------------------------------------------------
            if (data == null || data.isEmpty()) {
                //若为空，报错并启动帮助页面
                System.out.println("Data is empty");
                onHelpButtonClick();
            }else{
                //若不空，解析yaml文件
                String userName = (String) data.get("userName");
                String passWord = (String)data.get("passWord");
                String gameVersion = (String) data.get("gameVersion");
                String rootDir = (String)data.get("rootDir");
                int maxMemory = (int) data.get("maxMemory");
                int windowWidth = (int) data.get("windowWidth");
                int windowHeight = (int) data.get("windowHeight");
                String jreDir = (String)data.get("jreDir");
                String mcRuntimeDir = (String) data.get("mcRuntimeDir");
                String javaAgent = (String)data.get("javaAgent");
                //将yaml配置文件内容显示到输入框
                UserName.setText(userName);
                PassWord.setText(passWord);
                Version.setText(gameVersion);
                RootDir.setText(rootDir);
                MaxMemory.setText(String.valueOf(maxMemory));
                Width.setText(String.valueOf(windowWidth));
                Height.setText(String.valueOf(windowHeight));
                JreDir.setText(jreDir);
                McRuntimeDir.setText(mcRuntimeDir);
                JavaAgent.setText(javaAgent);
                //检查是否有自定义尺寸
                if(windowWidth != 854 || windowHeight != 480){
                    //有自定义尺寸，修改单选框
                    costumeSize.setSelected(true);
                    OnCostumeSizeSelected();
                }else{
                    //没有自定义尺寸，设置尺寸为默认
                    defaultSize.setSelected(true);
                    OnDefaultSizeSelected();
                }
            }
        }else{
            //若不存在，启动帮助页面并创建空文件
            onHelpButtonClick();
            if (configFile.createNewFile()) {
                //若创建配置文件成功
                System.out.println("Config file created successfully!");
            } else {
                //若创建配置文件失败
                System.out.println("Failed to create config file!");
            }
        }
    }

    @FXML
    protected void OnSelectionChanged(Event event) throws IOException {
        //--------------------------------------------------------------------------------
        //定义标签改变事件
        //--------------------------------------------------------------------------------
        writeConfig();
    }

    public void writeConfig() throws IOException {
        //--------------------------------------------------------------------------------
        //写配置文件
        //--------------------------------------------------------------------------------
        BufferedWriter writer = getBufferedWriter();
        //关闭写入器
        writer.close();
    }

    private BufferedWriter getBufferedWriter() throws IOException {
        //实例化写入器
        BufferedWriter writer = new BufferedWriter(new FileWriter("config.yml", false));
        //写入配置文件
        writer.write("userName: " + UserName.getText());
        writer.newLine();
        writer.write("passWord: " + PassWord.getText());
        writer.newLine();
        writer.write("gameVersion: " + Version.getText());
        writer.newLine();
        writer.write("rootDir: " + RootDir.getText());
        writer.newLine();
        writer.write("maxMemory: " + MaxMemory.getText());
        writer.newLine();
        writer.write("windowWidth: " + Width.getText());
        writer.newLine();
        writer.write("windowHeight: " + Height.getText());
        writer.newLine();
        writer.write("jreDir: " + JreDir.getText());
        writer.newLine();
        writer.write("mcRuntimeDir: " + McRuntimeDir.getText());
        writer.newLine();
        writer.write("javaAgent: " + JavaAgent.getText());
        return writer;
    }

    //--------------------------------------------------------------------------------
    //自定义窗口尺寸
    //--------------------------------------------------------------------------------
    public void OnDefaultSizeSelected() {
        //判断默认大小是否开启
        if(defaultSize.isSelected()){
            //默认大小开启，自定义大小关闭
            costumeSize.setSelected(false);
            //设置窗口大小选项为默认
            Width.setText("854");
            Height.setText("480");
            //锁定窗口大小
            Width.setDisable(true);
            Height.setDisable(true);
        }else{
            //默认大小未开启，判断自定义大小是否开启
            if(!costumeSize.isSelected()){defaultSize.setSelected(true);}
        }
    }
    public void OnCostumeSizeSelected() {
        //判断自定义大小是否开启
        if(costumeSize.isSelected()){
            //自定义大小开启，默认大小关闭
            defaultSize.setSelected(false);
            //解锁窗口大小
            Width.setDisable(false);
            Height.setDisable(false);
        }else{
            //自定义大小未开启，判断默认大小是否开启
            if(!defaultSize.isSelected()){costumeSize.setSelected(true);}
        }
    }
}