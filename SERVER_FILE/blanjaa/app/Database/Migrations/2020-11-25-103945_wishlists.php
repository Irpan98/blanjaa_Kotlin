<?php namespace App\Database\Migrations;

use CodeIgniter\Database\Migration;

class Wishlists extends Migration
{
	public function up()
	{
		$this -> forge -> addField([
			'id' =>[
				'type' => 'BIGINT',
				'constraint' => 20,
				'unsigned' => TRUE,
				'auto_increment' => TRUE,
			],
			'product_id' => [
				'type' => 'BIGINT',
				'constraint' => 20,

			],
			'user_id' => [
				'type' => 'BIGINT',
				'constraint' => 20,
			]

		]);

		$this->forge->addKey('id', TRUE);
		$this ->forge->createTable('wishlists', TRUE);
	}

	//--------------------------------------------------------------------

	public function down()
	{
		//
	}
}
