import axios from "axios";
import {appConfig} from "../config/config";

export default class PostService {
    static async getAll() {
        return await axios.get('/v1/posts');
    }

    static async getPage(page = 1, size = 5) {
        return await axios.get([appConfig.api.serverUrl, '/v1/posts'].join('/'), {
            params: {
                size: size,
                page: page
            }
        });
    }

    static async getMyPostsPage(page = 1, size = 5) {
        const config = {
            method: 'get',
            url: [appConfig.api.serverUrl, appConfig.api.posts.my].join('/'),
            params: {
                page: page,
                size: size
            },
            withCredentials: true
        };
        return axios(config);
    }

    static async getById(id) {
        return await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}`);
    }

    static async getCommentsByPostId(id) {
        return await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}/comments`);
    }

}