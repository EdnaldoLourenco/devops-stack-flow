resource "azurerm_resource_group" "rg-automation" {
  name     = var.rg-name
  location = var.rg-location
}

resource "azurerm_container_registry" "acr" {
  name                = "crautomationprd"
  resource_group_name = azurerm_resource_group.rg-automation.name
  location            = azurerm_resource_group.rg-automation.location
  sku                 = "Standard"
  admin_enabled       = false
}

resource "azurerm_service_plan" "plan-prd" {
  name                = "plan-prd"
  location            = azurerm_resource_group.rg-automation.location
  resource_group_name = azurerm_resource_group.rg-automation.name
  os_type             = "Linux"
  sku_name            = "B1"
}

resource "azurerm_linux_web_app" "app-prd" {
  name                = "app-prod001"
  location            = azurerm_resource_group.rg-automation.location
  resource_group_name = azurerm_resource_group.rg-automation.name
  service_plan_id     = azurerm_service_plan.plan-prd.id

  identity {
    type = "SystemAssigned"
  }

  site_config {
    container_registry_use_managed_identity = true
  }
}

resource "azurerm_role_assignment" "acr_pull" {
  scope                = azurerm_container_registry.acr.id
  role_definition_name = "AcrPull"
  principal_id         = azurerm_linux_web_app.app-prd.identity[0].principal_id
  depends_on           = [azurerm_linux_web_app.app-prd]
}
