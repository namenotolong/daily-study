const fs = require("fs");
const path = "/Users/guandata/Documents/workhome/fronthome/my-project/README.md"
let data = fs.readFileSync(path)

console.log(typeof data)

console.log(data.toString())


fs.readFile(path, (err, data) => {
    if (err) {
        return console.err(err.toString())
    }
    console.log(data.toString())
})