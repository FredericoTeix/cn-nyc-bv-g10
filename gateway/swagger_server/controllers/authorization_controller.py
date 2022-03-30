from typing import List

import connexion
from connexion.exceptions import OAuthProblem

from swagger_server.controllers import api_key_controller

def check_api_key(api_key, required_scopes):
    if not api_key:
        raise OAuthProblem('Invalid token')
    # TODO: Verify authorization
    # api_key_controller.consume_key(api_key, connexion.request.path, connexion.request.method)
    return {'uid': 100}
