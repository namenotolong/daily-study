class User {
    constructor(name) {
        this.name = name;
    }
    age = 12;
    static COMMON = "common"

    add(a,b) {
        return a+b;
    }
}
let user = new User("test")
console.log(user.name)
console.log(user.age)
console.log(User.COMMON)
User.prototype.history = "historyUser"
console.log(user.history)
console.log(user.add(1,2))

