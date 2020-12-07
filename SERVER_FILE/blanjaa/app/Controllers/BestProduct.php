<?php

namespace App\Controllers;


// use \Firebase\JWT\JWT;
use CodeIgniter\RESTful\ResourceController;

// use App\Controllers\Auth;

// header("Access-Control-Allow-Origin: *");
// header("Content-type: application/json; charset=UTF-8");
// header("Access-Control-Allow-Methods: POST");
// header("Access-Control-Max-Age: 3600");
// header("Access-Control-Allow-headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

class BestProduct extends ResourceController {

    protected $format = 'json';
    protected $modelName = 'App\Models\BestProduct_Model';

    protected $isSuccess = null;
    protected $message = null;
    protected $data = null;
    protected $status =null;
    protected $error =null;
    

    // public function __construct(){
    //     $this-> protect = new Auth();

    // }

    public function index(){

        // $secret_key = $this->protect->privateKey();

        // if($this->request->getServer('HTTP_AUTHORIZATION') == ""){
        //     $authHeader = null;

        //     $this -> isSuccess = false;
        //     $this -> message ="Access denied";
        //     $this -> status = 401;

        // }else{
        //     $authHeader = $this-> request->getServer('HTTP_AUTHORIZATION');
        // }

        // $arr = explode(" ", $authHeader);

        // $token = $arr[1];

        // if($token){
            try{
                // $decoded = JWT::decode($token, $secret_key,  array('HS256'));

                // if($decoded){

                    $data = $this -> model ->findAll();

                    if($data){
                        $this -> isSuccess = true;
                        $this -> message = "Data berhasil didapatkan";
                        $this -> data = $data;
                        $this -> status = 200;
                    }else{
                        $this -> isSuccess = false;
                        $this -> message = "Data tidak ditemukan";
                        $this -> status = 200;
            
            
                    }

                // }

            }catch(Exception $e){
                $output  = [
                    'message' => 'Access denied',
                    'error' => $e->getMessage
                ];
                return $this ->  respond($output, 401);
            }
        // }




        return $this -> showResponse();

    }

    public function showResponse(){

        $response = array(
            'isSuccess' => $this -> isSuccess, 
            'message' => $this -> message, 
            'data' => $this -> data, 
            'errors' => $this -> error, 
        );
    return $this ->respond($response, $this ->status);

    }


    public function create(){
        $validation = \Config\Services::validation();

        $id = $this -> request-> getPost('id');
        $product_id = $this -> request-> getPost('product_id');


        $data = array(
            'id' => $id,
            'product_id'=> $product_id,
        );

        if($validation-> run($data, 'bestProduct') == FALSE){
            $this -> isSuccess = false;
            $this -> error = $validation -> getErrors();
            $this -> message = "Ada Kesalahan";
            $this -> status = 200;        
        }
        else {
            $simpan = $this -> model-> insertBestProduct($data);

            if($simpan){
                $this -> isSuccess = true;
                $this -> message = "Success post data bestProduct";
                $this -> data = $data;
                $this -> status = 200;
            }else{
                $this -> isSuccess = false;
                $this -> message = "failed  post data bestProduct";
                $this -> status = 200;
            }

        }
        return $this -> showResponse();


    }

    // public function update($id = null){

    //     $validation = \Config\Services::validation();

    //     $bestProduct_name = isset($this -> request-> getRawInput()['bestProduct_name']) ? $this -> request-> getRawInput()['bestProduct_name']: null;

    //     $bestProduct_stock = isset($this -> request-> getRawInput()['bestProduct_stock']) ? $this -> request-> getRawInput()['bestProduct_stock']: null;

    //     $data = array(

    //         'bestProduct_name' => $bestProduct_name,
    //         'bestProduct_stock' => $bestProduct_stock

    //     );

    //     if($validation -> run($data, 'bestProduct') == FALSE){

    //         $this -> isSuccess = false;
    //         $this -> error = $validation -> getErrors();
    //         $this -> message = "Ada Kesalahan";
    //         $this -> status = 200;
    //     }else {

    //         $update = $this -> model-> updateBestProduct($data, $id);

    //         if($update){
    //             $this -> isSuccess = true;
    //             $this -> message = "Success update data bestProduct";
    //             $this -> data = $data;
    //             $this -> status = 200;
    //         }else{
    //             $this -> isSuccess = false;
    //             $this -> message = "failed  update data bestProduct";
    //             $this -> status = 200;
    //         }
    //     }


    // return $this ->  showResponse();

    // }


    public function delete($id = null){
        $hapus = $this -> model ->deleteBestProduct($id);
        if($hapus){
            $this -> isSuccess = true;
            $this -> message ="Success delete Data BestProduct";
            $this -> status = 200;
        }else{
            
            $this -> isSuccess = false;
            $this -> message = "Failed delete data bestProduct";
            $this -> status =200;

        }

    return $this ->  showResponse();
    }

}


?>