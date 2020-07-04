import axios from 'axios';
import http from '../utils/http';

// function findGoodsList() {
//     return axios.get('');
// }
//获取首页帖子列表
// export const posts = (obj) => {
//     console.log(obj);
//     obj.pageNum = obj.pageNum || '';
//     obj.fId = obj.fId || '';
//     obj.tId = obj.tId || '';
//     console.log(obj.tId,'obj********')
//     return http.get('/V1/es/posts/search?pageSize=' + obj.pageSize + '&pageNum=' + obj.pageNum + '&fId=' + obj.fId + '&tId=' + obj.tId + '&orderBy=' + obj.orderBy);
// };

export const findGoodsList = (data) => {
    // console.log(obj);
    // return http.get('http://localhost:61002/V1/goods/findGoodsList');
    return http.get('/V1/goods/findGoodsList');
};