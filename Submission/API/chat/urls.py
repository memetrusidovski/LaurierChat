from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('model/', views.message, name='message'),
]
