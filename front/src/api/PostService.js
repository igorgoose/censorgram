import axios from "axios";

export default class PostService {

    static async getAll() {
        return await axios.get('https://jsonplaceholder.typicode.com/posts');
    }

    static async getPage(page = 1, size = 5) {
        return await axios.get(`https://jsonplaceholder.typicode.com/posts`, {
            params : {
                _limit: size,
                _page: page
            }
        });
    }

    static async getById(id) {
        return await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}`);
    }

    static async getCommentsByPostId(id) {
        return await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}/comments`);
    }

}