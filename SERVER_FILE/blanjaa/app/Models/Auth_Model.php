<?php

namespace App\Models;

use CodeIgniter\Model;

class Auth_Model extends Model {
    protected $table = "users";
    
    public function register($data){
        $query = $this->db-> table($this->table)->insert($data);
        
        return $query;
    }

    public function login($email){
        $query = $this -> table($this -> table)
        ->where('email', $email)
        -> countAll();


        if($query> 0){
            $result = $this -> table($this -> table)
            ->where('email', $email)
            -> limit(1)
            -> get()
            -> getRowArray();
        }else {
            $result = array();
        }

        return $result;
        
    }
    public function addFirebaseToken($id, $data){
        $query = $this -> table($this -> table)
        ->where('id', $id)
        -> countAll();


        if($query> 0){
            $result = $this -> table($this -> table)
            ->where('id', $id)
            -> limit(1)
            -> get()
            -> getRowArray();
        }else {
            $result = array();
        }


        $result =  $this -> db -> table($this -> table)-> update($data, ['id' => $id]);
        
        

        return $result;


    }

    public function getAllUser($id = false) {

        if($id == false){
            return  $this ->findAll();
        }else{
            return $this -> getWhere(['id' => $id])->getRowArray();
        }
            

    }

    
    public function getUserByName($email) {


        return $this -> getWhere(['email' => $email])->getRowArray();
        
            

    }
}