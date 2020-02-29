import axios from 'axios'
import Qs from 'qs'

export default function ajax(url, data = {}, type = 'GET') {

    return new Promise(function (resolve, reject) {
        // 执行异步ajax请求
        let promise
        if (type === 'GET') {
            // 准备url query参数数据
            let dataStr = '' //数据拼接字符串
            Object.keys(data).forEach(key => {
                dataStr += key + '=' + data[key] + '&'
            })
            if (dataStr !== '') {
                dataStr = dataStr.substring(0, dataStr.lastIndexOf('&'))
                url = url + '?' + dataStr
            }
            // 发送get请求
            promise = axios.get(url)
        } else {
            // 发送post请求
            data = Qs.stringify(data)
            promise = axios.post(url, data, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
            // promise = axios.post(url, data, { responseType: 'blob' }, { headers: { 'Content-Type': 'application/json' } })

        }
        promise.then(function (response) {
            // 成功了调用resolve()
            resolve(response.data)
        }).catch(function (error) {
            //失败了调用reject()
            reject(error)
        })
    })
}

