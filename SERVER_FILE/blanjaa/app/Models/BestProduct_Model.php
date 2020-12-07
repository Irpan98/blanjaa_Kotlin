<?php

namespace App\Models;

use CodeIgniter\Model;

class BestProduct_Model extends Model {


    protected $table = 'bestproducts';

    public function getBestProduct($id = false) {

        if($id == false){
            return  $this ->findAll();
        }else{
            return $this -> getWhere(['user_id' => $id])->getRowArray();
        }
            

    }

    public function insertBestProduct($data){
        return $this -> db -> table($this -> table)-> insert($data);
    }

    public function updateBestProduct($data, $id){
        return $this -> db -> table($this -> table)-> update($data, ['id' => $id]);
    }

    public function deleteBestProduct( $id){
        return $this -> db -> table($this -> table)-> delete( ['id' => $id]);
    }
}



?>