<?php namespace Config;

class Validation
{
	//--------------------------------------------------------------------
	// Setup
	//--------------------------------------------------------------------

	/**
	 * Stores the classes that contain the
	 * rules that are available.
	 *
	 * @var array
	 */
	public $ruleSets = [
		\CodeIgniter\Validation\Rules::class,
		\CodeIgniter\Validation\FormatRules::class,
		\CodeIgniter\Validation\FileRules::class,
		\CodeIgniter\Validation\CreditCardRules::class,
	];

	/**
	 * Specifies the views that are used to display the
	 * errors.
	 *
	 * @var array
	 */
	public $templates = [
		'list'   => 'CodeIgniter\Validation\Views\list',
		'single' => 'CodeIgniter\Validation\Views\single',
	];



	//--------------------------------------------------------------------
	// Rules
	//--------------------------------------------------------------------

	public $product = [
		'name' => 'required',
		'description' => 'required',
		'price' => 'required',
		'categori_id' => 'required',
		'imagePath' => 'required',
	];

	public $product_errors = [
		'name' => ['required' => 'Product Name Harus diisi'	],
		'description' => ['required' => 'description  Harus diisi'	],
		'price' => ['required' => 'price  Harus diisi'	],
		'categori_id' => ['required' => 'categori_id  Harus diisi'	],
		'imagePath' => ['required' => 'Product Name Harus diisi'	],

	];

	public $wishList = [
		// 'id' => 'required',
		'product_id' => 'required',
		'user_id' => 'required',

	];

	public $wishList_errors = [
		// 'id' => ['required' => 'id  Harus diisi'	],
		'product_id' => ['required' => 'product_id  Harus diisi'	],
		'user_id' => ['required' => 'user_id  Harus diisi'	],
	];
	

	public $checkout = [
		// 'id' => 'required',
		'product_id' => 'required',
		'user_id' => 'required',
	];

	public $checkout_errors = [
		// 'id' => ['required' => 'id  Harus diisi'	],
		'product_id' => ['required' => 'product_id  Harus diisi'	],
		'user_id' => ['required' => 'user_id  Harus diisi'	],
	];

	public $history = [
		// 'id' => 'required',
		'product_id' => 'required',
		'user_id' => 'required',
	];

	public $history_errors = [
		// 'id' => ['required' => 'id  Harus diisi'	],
		'product_id' => ['required' => 'product_id  Harus diisi'	],
		'user_id' => ['required' => 'user_id  Harus diisi'	],
	];

	public $bestProduct = [
		// 'id' => 'required',
		'product_id' => 'required',
	];

	public $bestProduct_errors = [
		// 'id' => ['required' => 'id  Harus diisi'	],
		'product_id' => ['required' => 'product_id  Harus diisi'	],
	];
}
