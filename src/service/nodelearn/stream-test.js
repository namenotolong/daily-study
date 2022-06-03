var fs = require("fs");


const readPath = '/Users/guandata/Documents/workhome/fronthome/my-project/README.md';
const wirtePath = '/Users/guandata/Documents/workhome/fronthome/my-project/output.txt';

function readData(path, func) {
    // 创建可读流
    let data = '';
    let readerStream = fs.createReadStream(path);

    // 设置编码为 utf8。
    readerStream.setEncoding('UTF8');

    // 处理流事件 --> data, end, and error
    readerStream.on('data', function (chunk) {
        data += chunk;
    });

    readerStream.on('end', function () {
        func(data);
    });

    readerStream.on('error', function (err) {
        console.log(err.stack);
    });
}
function writeData(path, data) {
    // 创建一个可以写入的流，写入到文件 output.txt 中
    var writerStream = fs.createWriteStream(path);

    // 使用 utf8 编码写入数据
    writerStream.write(data, 'UTF8');

    // 标记文件末尾
    writerStream.end();

    // 处理流事件 --> finish、error
    writerStream.on('finish', function () {
        console.log("写入完成。");
    });

    writerStream.on('error', function (err) {
        console.log(err.stack);
    });
}

function copy(fromPath, targetPath) {
    let readerStream = fs.createReadStream(fromPath);
    let writeStream = fs.createReadStream(targetPath);
    readerStream.pipe(writeStream);
}


readData(readPath, data => {
    //console.log("result data: " + data)
    console.log(readPath + ' read success size: ' + data.length)
})

readData(wirtePath, data => {
    console.log(wirtePath + ' read success size: ' + data.length)
})
copy(readPath, wirtePath)
readData(wirtePath, data => {
    console.log(wirtePath + ' read success size: ' + data.length)
})
console.log("程序执行完毕");