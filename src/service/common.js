const axios = require('axios');

axios.defaults.withCredentials = true

axios.get('http://localhost:12345/universe/projects/time', {
    headers : {"Cookie": "language=zh_CN; sessionId=30d68fcf-da52-43cb-ba2a-75c1a22aeeab; grafana_session=f39caa46802e20b4c8380d25bcfaddc3"}
})
    .then(res => {
        console.log(res.headers)
    })
    .catch(error => {
        console.log(error);
    });