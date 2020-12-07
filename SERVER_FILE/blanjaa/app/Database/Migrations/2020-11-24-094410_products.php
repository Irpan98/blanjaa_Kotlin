<?php namespace App\Database\Migrations;

use CodeIgniter\Database\Migration;

class Products extends Migration
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
			'name' => [
				'type' => 'VARCHAR',
				'constraint' => 255

			],
			'description' =>[
				'type' => 'TEXT',

			],
			'price' =>[
				'type' => 'INT',
				'constraint' => 255

			],
			'categori_id' =>[
				'type' => 'INT',
				'constraint' => 255

			],
			'imagePath' =>[
				'type' => 'TEXT',

			],

		]);

		$this->forge->addKey('id', TRUE);
		$this ->forge->createTable('products', TRUE);
	}

	//--------------------------------------------------------------------

	public function down()
	{
		//
	}
}
