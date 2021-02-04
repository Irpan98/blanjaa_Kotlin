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
                    "dkwvv1SmSzu7_P8S0VzJHG:APA91bGd0YA-o0VpfjuA4EWWpodip0WNtkA0BpX-yKNmHyQkNJFcTH9E3ouVBDsEb_UfdCJNeXmthfi9N3IIKQVhcUM815lFFSjarnAtadeBaQFgXhhU3lbcCjGy6VupcVJ7uI63A1cZ"
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
