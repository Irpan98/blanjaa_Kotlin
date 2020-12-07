<?php

namespace App\Models;

use CodeIgniter\Model;

class History_Model extends Model {


    protected $table = 'histories';

    public function getHistory($id = false) {

        if($id == false){
            return  $this ->findAll();
        }else{
            return $this -> getWhere(['user_id' => $id])->getRowArray();
        }
            

    }

    public function insertHistory($data){
        return $this -> db -> table($this -> table)-> insert($data);
    }

    public function updateHistory($data, $id){
        return $this -> db -> table($this -> table)-> update($data, ['id' => $id]);
    }

    public function deleteHistory( $id){
        return $this -> db -> table($this -> table)-> delete( ['id' => $id]);
    }
}



?>