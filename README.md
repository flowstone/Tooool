# Tooool

### 特殊说明
#### 打包Jar包时注意事项
pom.xml文件中引入下方插件
```xml
 <!--打包第三方依赖包-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.3.0</version>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                        <configuration>
                            <archive>
                                <manifest>
                                    <mainClass>tech.xueyao.tooool.ToooolApplication</mainClass>
                                </manifest>
                            </archive>
                            <descriptorRefs>
                                <descriptorRef>jar-with-dependencies</descriptorRef>
                            </descriptorRefs>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
```
##### 运行
```mvn clean package```

##### 生成类似
```Tooool-1.0-SNAPSHOT-jar-with-dependencies.jar```这样的文件

#### 运行Jar包
``` bash
/Users/simonxue/Library/Java/JavaVirtualMachines/openjdk-21.0.1/Contents/Home/bin/java 
--module-path "/Users/simonxue/Developer/Java/javafx-sdk-23.0.1/lib" 
--add-modules "javafx.controls,javafx.fxml" 
-jar /Users/simonxue/Code/ueYao-Workspace/Tooool/target/Tooool-1.0-SNAPSHOT-jar-with-dependencies.jar
```
##### 说明
###### 1.Java版本不要低于21 
```/Users/simonxue/Library/Java/JavaVirtualMachines/openjdk-21.0.1/Contents/Home/bin/java```
###### 2.引入JavaFX的SDK 
```--module-path "/Users/simonxue/Developer/Java/javafx-sdk-23.0.1/lib" ```
###### 3.主要JavaFX模块
```--add-modules "javafx.controls,javafx.fxml"```
###### 4. Jar包
```/Users/simonxue/Code/ueYao-Workspace/Tooool/target/Tooool-1.0-SNAPSHOT-jar-with-dependencies.jar```