<?php
namespace App\Controllers;

use CodeIgniter\RESTful\ResourceController;

class SendMessage extends ResourceController {
    protected $format = 'json';

    
    public function index(){
        
        $title = $this -> request-> getPost('title');

        
        if(!empty($title)){
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
            CURLOPT_POSTFIELDS =>'{
                "registration_ids":
                [
                    "flP6k27ZT42OschoScja4X:APA91bFc90TW5_ut0buCSmX8nOUD6xsF0HwrYEp3XmymGYUnzou04PI5udF3W6gURUcPd9MdiDX8H5nUAn265Mo9qc8aGB-tABT-A4dzqM5VoUiJbYf5m1Q4aUcc6b-nDI_um-_KfKJr",
                    "fsa_5zXEQfya1PNYok3a_h:APA91bG59HiQgL5YEoY-FXd0VR85_Qo-k1Muw73GmIH_LjIrCtjy654lgK-5lLli0RBt7Outz7SWmVaJ4-0PlHOexv7TkTYhvbISjDDO4nv7JJsKvHGriJCZ885G2IR7vfrtcvvKerFO",
                    "esPQrKy-T1q1-8WUIvZJbI:APA91bEfGVcLyr3Hn5-xDxgqb62QcEESK1VQi7eR8xIp5P-916cZKo-UXLW-KzDdO-9FDxqKdEqKvkTWMFjZX29uSe1BizvNOujcu57zWAuTfJ7Xe78jfzBxUBgI7X4QlIIr1wzXPHcj"
                ],    
    
                "data":{
                    "title":"Ini adalah title aaaaa data ",
                    "body": "ini adalah body data" 
                }
            }
                    
                ',
            CURLOPT_HTTPHEADER => array(
                'Authorization: key=AAAAYLvxTZs:APA91bEOcbdZKEd0_KwQ2vhD21DFc9ozz9-nNp-ntEBM6WMDY0FC4xXRvxuHSFKChUvQ9mrfSt-v05bpl2LNne_OcO2tusqqtnQP9RQd6s0I7xJX80QcgzIm5ruTR96_CHNKOSVFq_OJ',
                'Content-Type: application/json'
            ),
            ));
    
            $response = curl_exec($curl);
    
            curl_close($curl);
            return $this-> setResponse("true","null");
        }else{
            return $this -> setResponse("false", "tidak boleh kosong");
        }




     }

     function setResponse($isSuccess, $message){
         $result = array(
             'isSuccess' => $isSuccess,
             'message' => $message
         );
         echo json_encode($result);
     }
}





?>
