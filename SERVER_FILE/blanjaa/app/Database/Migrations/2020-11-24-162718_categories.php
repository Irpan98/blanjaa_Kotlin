<?php namespace App\Database\Migrations;

use CodeIgniter\Database\Migration;

class Categories extends Migration
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
			'backgroundPath' =>[
				'type' => 'TEXT',

			],

		]);

		$this->forge->addKey('id', TRUE);
		$this ->forge->createTable('categories', true);
	}

	//--------------------------------------------------------------------

	public function down()
	{
		//
	}
}
