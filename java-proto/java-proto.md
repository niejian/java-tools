# java与protobuf集成
## maven管理protobuf文件
通过相关的maven的相关插件，将protobuf文件放在默认的指定文件夹中，通过指令：`mvn clean install -Dmaven.test.skip=true` 在target目录中生成相关的java文件
### maven 插件配置
需要在`properties`标签中声明以下属性值：**protobuf.version**, **grpc.version**

```xml
<build>
    <extensions>
        <extension>
            <groupId>kr.motd.maven</groupId>
            <artifactId>os-maven-plugin</artifactId>
            <version>1.5.0.Final</version>
        </extension>
    </extensions>
    <plugins>
        <plugin>
            <groupId>org.xolstice.maven.plugins</groupId>
            <artifactId>protobuf-maven-plugin</artifactId>
            <version>0.6.1</version>
            <configuration>
                <!--protobuf 文件路径-->
                <protoSourceRoot>${project.basedir}/src/main/resources/proto</protoSourceRoot>
                <!--生成的java文件目录-->
                <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
                <clearOutputDirectory>false</clearOutputDirectory>
                <protocArtifact>com.google.protobuf:protoc:${protobuf.version}:exe:${os.detected.classifier}</protocArtifact>
                <pluginId>grpc-java</pluginId>
                <pluginArtifact>io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}</pluginArtifact>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                        <goal>compile-custom</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```