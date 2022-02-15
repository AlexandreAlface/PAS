@extends('layouts.app')

@section('content')
@if (Route::has('login'))
@auth
<div class="card-header">{{ __('Editar comidas') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
    <div class="col-lg-1">
        <a class="btn btn-primary" href="{{ url('comidas') }}"> Back</a>
    </div>
    @if (session('status'))
    <div class="alert alert-success" role="alert">
        {{ session('status') }}
    </div>
    @endif

    <form method="post" action="{{ route('mesas.update',$mesas->id) }}">
        @method('PATCH')
        @csrf
        <div class="form-group">
            <label for="txtNome">Nome</label>
            <input type="text" class="form-control" id="txtNome" placeholder="Nome" name="txtNome" value="{{ $comidas->nome }}">
        </div>
        <div class="form-group">
            <label for="txtValor">Valor</label>
            <input type="text" class="form-control" id="txtValor" placeholder="Valor" name="txtValor" value="{{ $comidas->valor }}">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    {{ __('You are logged in!') }}
</div>

<div class="hidden fixed top-0 right-0 px-6 py-4 sm:block">
    <a href="{{ url('/home') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Home</a>
    @else
    <a href="{{ route('login') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Log in</a>

    @if (Route::has('register'))
    <a href="{{ route('register') }}" class="ml-4 text-sm text-gray-700 dark:text-gray-500 underline">Register</a>
    @endif
    @endauth
</div>
@endif
@endsection