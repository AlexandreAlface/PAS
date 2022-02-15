@extends('layouts.app')

@section('content')
@if (Route::has('login'))
@auth
<div class="card-header">{{ __('Criar Utilizador') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
    @if (session('status'))
        <div class="alert alert-success" role="alert">
            {{ session('status') }}
        </div>
    @endif
<form action="{{ route('utilizadores.store') }}" method="POST">
        @csrf
        <div class="form-group">
            <label for="txtUsername">Username:</label>
            <input type="text" class="form-control" id="txtUsername" placeholder="Username" name="txtUsername">
        </div>
        <div class="form-group">
            <label for="txtPassword">Password:</label>
            <input type="text" class="form-control" id="txtPassword" placeholder="Password" name="txtPassword">
        </div>
        <div class="form-group">
            <label for="txtName">Nome:</label>
            <input type="text" class="form-control" id="txtName" placeholder="Nome" name="txtName">
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