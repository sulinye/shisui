import http from '../utils/http';

export const login = (data) => {
    console.log(data);
    return http.post('/V1/auth/login',data);
};

export const getImageData = () => {
    return http.get('/V1/auth/getImageData');
}