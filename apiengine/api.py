from tastypie.resources import ModelResource
from tastypie.exceptions import ImmediateHttpResponse
import goslate


class WordResource(ModelResource):

    def obj_create(self, bundle, **kwargs):
        word = bundle.data.get('original')
        language = bundle.data.get('language')
        gs = goslate.Goslate()
        response_data = {}
        response_data["translated"] = gs.translate(word, language)
        raise ImmediateHttpResponse(response=self.create_response(bundle.request, response_data))
