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

class Products extends ResourceController {

    protected $format = 'json';
    protected $modelName = 'App\Models\Product_Model';
    protected $userModel = 'App\Models\Auth_Model';

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

        $product_name = $this -> request-> getPost('name');
        $product_description = $this -> request-> getPost('description');
        $product_price = $this -> request-> getPost('price');
        $product_category = $this -> request-> getPost('categori_id');
        $product_image = $this -> request-> getPost('imagePath');

        $data = array(
            'name' => $product_name,
            'description'=> $product_description,
            'price' => $product_price,
            'categori_id' => $product_category,
            'imagePath' => $product_image,
        );

        if($validation-> run($data, 'product') == FALSE){
            $this -> isSuccess = false;
            $this -> error = $validation -> getErrors();
            $this -> message = "Ada Kesalahan";
            $this -> status = 200;        
        }
        else {
            $simpan = $this -> model-> insertProduct($data);

            if($simpan){
                $this -> isSuccess = true;
                $this -> message = "Success post data product";
                $this -> data = $data;
                $this -> status = 200;
            }else{
                $this -> isSuccess = false;
                $this -> message = "failed  post data product";
                $this -> status = 200;
            }

        }
        return $this ->SendMessage($product_name);
        // return $this -> showResponse();


    }


    public function SendMessage($product_name){
        

        $db      = \Config\Database::connect();
        $query = $db->query('SELECT token FROM users');


        $dataToken  = $query->getResultArray();  
        // $listToken  =array(
        //     $dataToken['token']
        // );

        
        $listToken = array();
        
        // $listToken = array_column($dataToken, 'token');

        foreach ($dataToken as $key => $value){
            
            array_push($listToken, str_replace('"', '', $value['token']));
        }


        
        if(!empty($product_name)){
            $curl = curl_init();

            curl_setopt_array($curl, array(
                CURLOPT_URL => 'https://fcm.googleapis.com/fcm/send',
                CURLOPT_RETURNTRANSFER => true,
                CURLOPT_ENCODING => '',
                CURLOPT_MAXREDIRS => 10,
                CURLOPT_TIMEOUT => 0,
                CURLOPT_FOLLOWLOCATION => true,
                CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                CURLOPT_CUSTOMREQUEST => 'POST',
                CURLOPT_POSTFIELDS => sprintf(
                '{
                    "registration_ids":
                    [
                        "fG2lFIDGSIOY4gF7b5OAAH:APA91bGSWxBI2p-bokkbjU3ucHK3EYCWtLAI2nvXCsMTTVu9fq5fvXfIpyt6FboluizeQy8-k4Vk7rXVbr6_cA6-nZ_T3bc9OB6iXhgx_FiRhz_0FQFsCCc48NyIexUFXFdEITWglbWB"
                    ],                    
                    "data":{
                        "title:'.$listToken.'",
                        "body": "Product baru ditambahkan : '.$product_name.'"
                    }
                }',$listToken),
                CURLOPT_HTTPHEADER => array(
                    "Authorization: key=AAAAYLvxTZs:APA91bEOcbdZKEd0_KwQ2vhD21DFc9ozz9-nNp-ntEBM6WMDY0FC4xXRvxuHSFKChUvQ9mrfSt-v05bpl2LNne_OcO2tusqqtnQP9RQd6s0I7xJX80QcgzIm5ruTR96_CHNKOSVFq_OJ",
                    "Content-Type: application/json"
                ),
            ));
    
            $response = curl_exec($curl);
    
            curl_close($curl);
            return $this-> setres("true","", $listToken);
        }else{
            return $this -> setres("false", "tidak boleh kosong", "null");
        }

        // $listToken = ['awww','baaa']);

        
        // if(!empty($product_name)){
        //     $curl = curl_init();

        //     curl_setopt_array($curl, array(
        //         CURLOPT_URL => 'https://fcm.googleapis.com/fcm/send',
        //         CURLOPT_RETURNTRANSFER => true,
        //         CURLOPT_ENCODING => '',
        //         CURLOPT_MAXREDIRS => 10,
        //         CURLOPT_TIMEOUT => 0,
        //         CURLOPT_FOLLOWLOCATION => true,
        //         CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        //         CURLOPT_CUSTOMREQUEST => 'POST',
        //         CURLOPT_POSTFIELDS => sprintf(
        //         '{
        //             "registration_ids":
        //             [
        //                 "flP6k27ZT42OschoScja4X:APA91bFc90TW5_ut0buCSmX8nOUD6xsF0HwrYEp3XmymGYUnzou04PI5udF3W6gURUcPd9MdiDX8H5nUAn265Mo9qc8aGB-tABT-A4dzqM5VoUiJbYf5m1Q4aUcc6b-nDI_um-_KfKJr",
        //                 "fsa_5zXEQfya1PNYok3a_h:APA91bG59HiQgL5YEoY-FXd0VR85_Qo-k1Muw73GmIH_LjIrCtjy654lgK-5lLli0RBt7Outz7SWmVaJ4-0PlHOexv7TkTYhvbISjDDO4nv7JJsKvHGriJCZ885G2IR7vfrtcvvKerFO",
        //                 "esPQrKy-T1q1-8WUIvZJbI:APA91bEfGVcLyr3Hn5-xDxgqb62QcEESK1VQi7eR8xIp5P-916cZKo-UXLW-KzDdO-9FDxqKdEqKvkTWMFjZX29uSe1BizvNOujcu57zWAuTfJ7Xe78jfzBxUBgI7X4QlIIr1wzXPHcj",
        //                 "fPkZsG1uQ_Og2JZdJHv6rM:APA91bE_HQ-kTazJMgw84OyvGD_D11HnS0Nho0lZEnFqbb3JaZp-634B7BcEh6VDNuv4T4TcyFdjE7Gu_Jhfn5eVa9Kk9_lBhBX3v-_9j0iwX1NRPYRxSpMhpI5sQdXUS6HeIKu5f3Vw",
        //                 "e0E0nyYiSAGc1adM3S358z:APA91bECA02Uazf4EgsHaImX75xtdcJ74oOciSoQC1gnKze5H9gJHN1qrvg_GvgaMkelNd152KUAgoC-WuYJQc1EjGzvya6FiK6R4mOKZp8pz2xNvX9dYSM2KOXClegPF2tM1-PBJ98h"
        //             ],                    
        //             "data":{
        //                 "title":"Ini adalah title aaaaa data ",
        //                 "body": "Product baru ditambahkan : '.$product_name.'"
        //             }
        //         }',$listToken),
        //         CURLOPT_HTTPHEADER => array(
        //             "Authorization: key=AAAAYLvxTZs:APA91bEOcbdZKEd0_KwQ2vhD21DFc9ozz9-nNp-ntEBM6WMDY0FC4xXRvxuHSFKChUvQ9mrfSt-v05bpl2LNne_OcO2tusqqtnQP9RQd6s0I7xJX80QcgzIm5ruTR96_CHNKOSVFq_OJ",
        //             "Content-Type: application/json"
        //         ),
        //     ));
    
        //     $response = curl_exec($curl);
    
        //     curl_close($curl);
        //     return $this-> setres("true",sprintf('registration_ids: %s ', json_encode($listToken)), $listToken);
        // }else{
        //     return $this -> setres("false", "tidak boleh kosong", "null");
        // }




     }
     function setres($isSuccess, $message, $dataToken){
        $result = array(
            'isSuccess' => $isSuccess,
            'message' => $message,
            'dataToken' => $dataToken
        );
        echo json_encode($result);
    }


}

    // public function update($id = null){

    //     $validation = \Config\Services::validation();

    //     $product_name = isset($this -> request-> getRawInput()['product_name']) ? $this -> request-> getRawInput()['product_name']: null;

    //     $product_stock = isset($this -> request-> getRawInput()['product_stock']) ? $this -> request-> getRawInput()['product_stock']: null;

    //     $data = array(

    //         'product_name' => $product_name,
    //         'product_stock' => $product_stock

    //     );

    //     if($validation -> run($data, 'product') == FALSE){

    //         $this -> isSuccess = false;
    //         $this -> error = $validation -> getErrors();
    //         $this -> message = "Ada Kesalahan";
    //         $this -> status = 200;
    //     }else {

    //         $update = $this -> model-> updateProduct($data, $id);

    //         if($update){
    //             $this -> isSuccess = true;
    //             $this -> message = "Success update data product";
    //             $this -> data = $data;
    //             $this -> status = 200;
    //         }else{
    //             $this -> isSuccess = false;
    //             $this -> message = "failed  update data product";
    //             $this -> status = 200;
    //         }
    //     }


    // return $this ->  showResponse();

    // }


    // public function delete($id = null){
    //     $hapus = $this -> model ->deleteProduct($id);
    //     if($hapus){
    //         $this -> isSuccess = true;
    //         $this -> message ="Success delete Data Product";
    //         $this -> status = 200;
    //     }else{
            
    //         $this -> isSuccess = false;
    //         $this -> message = "Failed delete data product";
    //         $this -> status =200;

    //     }

    // return $this ->  showResponse();
    // }




?>