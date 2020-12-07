<?php

namespace App\Models;

use CodeIgniter\Model;

class Checkout_Model extends Model {


    protected $table = 'checkouts';

    public function getCheckout($id = false) {

        if($id == false){
            return  $this ->findAll();
        }else{
            return $this -> getWhere(['user_id' => $id])->getRowArray();
        }
            

    }

    public function insertCheckout($data){
        return $this -> db -> table($this -> table)-> insert($data);
    }

    public function updateCheckout($data, $id){
        return $this -> db -> table($this -> table)-> update($data, ['id' => $id]);
    }

    public function deleteCheckout( $id){
        return $this -> db -> table($this -> table)-> delete( ['id' => $id]);
    }
}



?>