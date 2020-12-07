<?php

namespace App\Models;

use CodeIgniter\Model;

class Category_Model extends Model {


    protected $table = 'categories';

    public function getCategory($id = false) {

        if($id == false){
            return  $this ->findAll();
        }else{
            return $this -> getWhere(['id' => $id])->getRowArray();
        }
            

    }

    // public function insertCategory($data){
    //     return $this -> db -> table($this -> table)-> insert($data);
    // }

    // public function updateCategory($data, $id){
    //     return $this -> db -> table($this -> table)-> update($data, ['id' => $id]);
    // }

    // public function deleteCategory( $id){
    //     return $this -> db -> table($this -> table)-> delete( ['id' => $id]);
    // }
}



?>