<?php

namespace App\Models;

use CodeIgniter\Model;

class Product_Model extends Model {


    protected $table = 'products';

    public function getProduct($id = false) {

        if($id == false){
            return  $this ->findAll();
        }else{
            return $this -> getWhere(['id' => $id])->getRowArray();
        }
            

    }

    public function insertProduct($data){
        return $this -> db -> table($this -> table)-> insert($data);
    }

    public function updateProduct($data, $id){
        return $this -> db -> table($this -> table)-> update($data, ['id' => $id]);
    }

    public function deleteProduct( $id){
        return $this -> db -> table($this -> table)-> delete( ['id' => $id]);
    }
}



?>