import Axios from 'axios'

export default class RestClient {
  constructor(baseUrl) {
    this.baseUrl = baseUrl;
    this.axios = Axios.create({
      baseURL: baseUrl,
      headers: {
        'Content-Type': 'application/json',
      }
    })
  }
  
  async post(path, payload) {
    return this.axios.post(path, payload);
  }
  async get(path) {
    return this.axios.get(path);
  }
  async put(path,payload) {
    return this.axios.put(path,payload);
  }
  async delete(path,payload) {
    return this.axios.delete(path,payload);
  }
}
