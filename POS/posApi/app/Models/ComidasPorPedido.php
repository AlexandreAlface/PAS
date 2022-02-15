<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ComidasPorPedido extends Model
{
    protected $table = 'Comidas_Por_Pedidos';

    protected $fillable = ['idPedido', 'idComida', 'Quantidade'];
}

