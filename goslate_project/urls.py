from django.conf.urls import include, url
from apiengine.api import WordResource

words_resource = WordResource()

urlpatterns = [
    url(r'^api/', include(words_resource.urls)),
]
