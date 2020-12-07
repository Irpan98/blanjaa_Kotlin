<?php

namespace App\Models;

use CodeIgniter\Model;

class WishList_Model extends Model {


    protected $table = 'wishlists';

    public function getWishList($id = false) {

        if($id == false){
            return  $this ->findAll();
        }else{
            return $this -> getWhere(['user_id' => $id])->getRowArray();
        }
            

    }

    public function insertWishList($data){
        return $this -> db -> table($this -> table)-> insert($data);
    }

    public function updateWishList($data, $id){
        return $this -> db -> table($this -> table)-> update($data, ['id' => $id]);
    }

    public function deleteWishList( $id){
        return $this -> db -> table($this -> table)-> delete( ['id' => $id]);
    }
}



?>