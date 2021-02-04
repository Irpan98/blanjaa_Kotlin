<?php namespace App\Controllers;

class Home extends ResourceController
{
	public function index()
	{
		$this -> isSuccess = true;
		$this -> message = "Data berhasil didapatkan";
		$this -> data = $data;
		$this -> status = 200;
        return $this -> showResponse();
	}

	public function showResponse(){

        $response = array(
            'isSuccess' => ""
        );
    return $this ->respond($response, $this ->status);

    }


}
