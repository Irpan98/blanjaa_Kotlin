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

class SendMessage2 extends ResourceController {

    protected $format = 'json';
    protected $modelName = 'App\Models\Checkout_Model';

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




        // return $this -> showResponse();

    }

}


?>