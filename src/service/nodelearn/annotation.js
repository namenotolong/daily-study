function testable(target) {
    target.isTestable = true;
}

@testable
class Teacher {
    constructor (name) {
        this.name = name;
    }

    say (data) {
        console.log(this.name + " say " +data)
    }
}


let a = new Teacher("xiaohong")
a.say("hello world")