import axios from "axios";
import {appConfig} from "../config/config";

export default class AuthService {

    static async login(username, password) {
        const data = new FormData();
        data.append('username', username);
        data.append('password', password);
        const config = {
            method: 'post',
            url: [appConfig.api.serverUrl, appConfig.api.login].join('/'),
            data : data,
            withCredentials: true
        };
        return axios(config);
    }
}