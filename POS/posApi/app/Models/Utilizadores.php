<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Utilizadores extends Model    
{
    protected $table = 'utilizadores';

    protected $fillable = ['username','password','name'];
}
