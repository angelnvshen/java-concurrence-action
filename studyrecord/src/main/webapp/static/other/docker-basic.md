#### docker file

**FROM** 基于哪个镜像
**RUN** 用来执行命令行命令，因为每一次run 都会建立一层镜像，所以尽量将命令在一个run命令中执行，执行后还可以清理一下无用的文件。
**WORKDIR** 是指定工作目录
**CMD**和**ENTRYPOINT**的区别都是容器启动命令，不同的是后者可以追加命令
**VOLUMN**是定义卷
**COPY**和**ADD**都有复制文件的意思，但是add比copy的功能多，例如可以解压文件
**Onbuild** 子镜像引用父镜像时在build镜像时，会触发父镜像的onbuild指令

#### 容器停止

docker stop | rm containerId

docker container prune  清理所有处于终止状态的容器

docker rm $(docker ps -q ) 批量删除容器