let [a, b, c] = [1, 2, 3];

let s = Symbol("system")
console.log(typeof s)
console.log(s.toString())

let target = {
    name: 'Tom',
    age: 24
}
let handler = {
    get: function (target, key) {
        console.log('getting ' + key);
        return target[key]; // 不是target.key
    },
    set: function (target, key, value) {
        console.log('setting ' + key);
        target[key] = value;
    }
}
let proxy = new Proxy(target, handler)
proxy.name     // 实际执行 handler.get
proxy.age = 25


function sub(a, b) {
    return a + b;
}
handler = {
    apply: function (target, ctx, args) {
        console.log('handle apply');
        return Reflect.apply(...arguments);
    }
}
proxy = new Proxy(sub, handler)
console.log(proxy(2, 1))

console.log(Reflect.get(target, "name"))