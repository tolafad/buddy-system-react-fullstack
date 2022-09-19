import axios from 'axios';

const USER_API_BASE_URL = "http://buddy-env-3.eba-epkynjas.eu-west-1.elasticbeanstalk.com/api/v1/buddy-system";
//const USER_API_BASE_URL = "http://localhost:5000/api/v1/buddy-system";

class UserService {

    getUsers(){
        return axios.get(USER_API_BASE_URL + '/users');
    }

    createUser(user){
        return axios.post(USER_API_BASE_URL + '/add-user' , user);
    }
        
    getUserById(userId){
        return axios.get(USER_API_BASE_URL + '/user/' + userId);
    }
    
    generateBuddyById(userId){
        return axios.get(USER_API_BASE_URL + '/user/' + userId + '/buddy');
    }
    
    updateUser(user, userId){
        return axios.put(USER_API_BASE_URL + '/update-user/'+ userId, user);
    }

    deleteUser(userId){
        return axios.delete(USER_API_BASE_URL + '/remove-user/' + userId);
    }
}

export default new UserService()